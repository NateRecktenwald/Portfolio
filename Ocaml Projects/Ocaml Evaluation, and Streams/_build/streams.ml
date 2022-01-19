(* The code below is from Professor Eric Van Wyk. *)
type 'a hidden = Value of 'a
               | Thunk of (unit -> 'a)

type 'a lazee = 'a hidden ref

let delay (unit_to_x: unit -> 'a) : 'a lazee =
  ref ( Thunk unit_to_x )

let force (l: 'a lazee) : unit = match !l with
  | Value _ -> ()
  | Thunk f -> l := Value (f ())

let demand (l: 'a lazee) : 'a =
  force l;
  match !l with
  | Value v -> v
  | Thunk _ -> failwith "This should not happen."

type 'a stream = Cons of 'a * 'a stream lazee

let rec take (n: int) (s: 'a stream) : 'a list =
  match n with
  | 0 -> []
  | _ -> (match s with
          | Cons (h, t) -> h :: take (n-1) (demand t)
         )

let rec map (f: 'a -> 'b) (s: 'a stream) : 'b stream =
  match s with
  | Cons (h, t) -> Cons (f h, delay (fun () -> map f (demand t) ) )

let rec zip (f: 'a -> 'b ->'c) (s1: 'a stream) (s2: 'b stream) :
          'c stream =
  match s1, s2 with
  | Cons (h1, t1), Cons (h2, t2) ->
     Cons (f h1 h2, delay (fun () -> zip f (demand t1) (demand t2)))

let rec filter (f: 'a -> bool) (s: 'a stream) : 'a stream = 
  match s with
  | Cons (h, t) ->
     let rest = delay (fun () -> filter f (demand t))
     in
     if f h 
     then Cons (h, rest)
     else demand rest

let rec from n =
  Cons ( n, delay ( fun () -> from (n+1) ) )

let nats = from 1

(* The code below is from Nathan Recktenwald *)

let rec cubes_from (n: int) : int stream = 
  Cons ((n * n * n) , delay (fun () -> cubes_from (n + 1)))

let rec cubes_from_zip (n: int) : int stream =
  zip (fun x y -> x * y) 
      (zip (fun x y -> x * y) (Cons (n, delay (fun () -> from (n + 1)))) 
                              (Cons (n, delay (fun () -> from (n + 1)))))
      (Cons (n, delay (fun () -> from (n + 1))))

let rec cubes_from_map (n: int) : int stream = 
  map (fun x -> x * x * x) (Cons (n, delay (fun () -> from (n + 1))))

let rec drop (n: int) (s: 'a stream) : 'a stream =
  match n,s with 
  | (0, _) -> s
  | (num, Cons (h, t)) -> drop (num - 1) (demand t)

let rec drop_until (f: 'a -> bool) (s: 'a stream) : 'a stream = 
  match s with 
  | Cons (h, t) -> if f h 
                   then Cons (h, t)
                   else drop_until f (demand t)

let rec arith_seq (x: int) (d: int) : int stream = 
  Cons (x, delay (fun () -> arith_seq (x + d) d))

let sift (n: int) (s: int stream) : int stream =
  filter (fun x -> not (x mod n = 0)) s

let rec sieve (s: int stream) : int stream = 
  match s with 
  | Cons (h, t) -> Cons (h, delay (fun () -> sieve (sift h (demand t))))

let primes = sieve (from 2)
