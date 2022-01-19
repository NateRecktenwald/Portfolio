
(* Part 3. Rose trees *)

type 'a rose_tree = Rose of 'a * 'a rose_tree list

let t1 : int rose_tree =
  Rose (2, [ Rose (1, [ Rose (5, []); Rose (7, []) ] );
               Rose (3, []);
               Rose (4, []);
             ]   )

let t2 : string rose_tree = 
  Rose ("r", [ Rose ("abc", [ Rose ("xy", []); Rose ("z", []) ] );
               Rose ("hello", []);
               Rose ("goodbye", [])
             ]
    )

let t3 : char rose_tree = 
  Rose ('r', [ Rose ('a', [ Rose ('m', []); Rose ('n', []) ] );
               Rose ('b', [ Rose ('o', []); Rose ('p', []) ] );
               Rose ('c', [ Rose ('x', []); Rose ('y', []) ] )
             ]
    )

let t4 : int rose_tree = Rose (4, [])

let rec reduce (f: 'a -> 'b list -> 'b) (tree: 'a rose_tree) = 
  match tree with 
  | Rose(v, lst) -> f v (List.map (reduce f) lst) 

let size (tree: 'a rose_tree): int = 
  reduce (fun first lst -> 1 + (List.fold_left (+) 0 lst)) tree

let sum (tree: int rose_tree): int = 
  reduce (fun first lst -> first + (List.fold_left (+) 0 lst)) tree

let product (tree: int rose_tree): int = 
  reduce (fun first lst -> first * (List.fold_left ( * ) 1 lst)) tree

let char_count (tree: string rose_tree): int = 
  String.length(reduce (fun first lst -> first ^ (List.fold_right (fun accum s -> accum^s) lst "")) tree)

let height (tree: 'a rose_tree): int = 
  let maximum lst = 
    List.fold_left max 0 lst
  in
  reduce (fun first lst -> 1 + maximum lst) tree       


let perfect_balance _ = raise(Failure "perfect_balance not complete") (* (tree: 'a rose_tree): bool = 
  let f (lst: (bool * int) list): bool * int) =
    let split_list l = match (List.split l) with 
                    | (bools, heights) -> let bs = bools 
                                          let hs = heights 
    in 
  in
  let helper (t: 'a rose_tree) =
    reduce f t
  in
  match (helper tree) with 
  | (isbal, _) -> isbal

*)

    

let maximum (tree: int rose_tree): int option = 
 Some (reduce (fun first lst -> List.fold_left max first lst) tree)
                           
