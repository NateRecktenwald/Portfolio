
(* List.map   List.filter  List.fold_left   List.fold_right *)
open Char 

let sum (xs: int list) : int = 
	List.fold_left (+) 0 xs

let sumf (xs: float list) : float = 
	List.fold_left (+.) 0.0 xs	

let product (xs: int list) : int = 
	List.fold_left ( * ) 1 xs

let productf (xs: float list) : float = 
	List.fold_left ( *. ) 1.0 xs

let minimum (a: 'a list) : 'a = 
	match a with 
	| [] -> raise (Invalid_argument "minimum: the list is empty")
	| x::[] -> x
	| x::xs -> List.fold_left (fun min_sofar x' -> if x' < min_sofar then x' else min_sofar) x xs

let longest (ss: string list) : string =
	match ss with 
	| [] -> raise (Invalid_argument "longest: the list is empty")
	| s::[] -> s
	| s::ss -> List.fold_left (fun long_sofar s' -> if String.length(s') > String.length(long_sofar) then s' else long_sofar) s ss

let append (x: 'a list) (y: 'a list) : 'a list =
	List.fold_right (fun accum x -> accum::x) x y

let elem x lst = 
	if((List.filter (fun y -> (x = y)) lst) = []) then
		false else true

let excited (x: string list) : string list =
	List.map (fun y -> y^"!") x

let suffix (x: string) (xs: string list) : string list = 
	List.map (fun y -> y^x) xs

let excited' (lst: string list) : string list =
	suffix "!" lst

let lengths (lst: string list) : int list =
	List.map (fun y -> String.length(y)) lst

let length_pairs (lst: string list) : (string * int) list = 
	List.map (fun y -> (y,String.length(y))) lst

let capitalize (lst: string list) : string list =
	List.map (fun x -> String.capitalize_ascii(x)) lst

let all_odds (lst: int list) : int list = 
	List.filter (fun y -> not ((y mod 2) = 0)) lst

let all_capitalized (lst: string list) : string list =
	let f x = if (x = "")
	then raise (Invalid_argument "all_capitalized: empty string")
	else not (lowercase_ascii(x.[0]) = x.[0])
	in 	
	List.filter f lst

let rec check_square (x: int) : int =
	if x = 0 then 0 else 
		let n = check_square (x - 1) in 
			if ((n+1)*(n+1) <= x) then n+1 else n
	 
let all_squares (xs: int list) : int list =
	let f x = if (check_square(x) * check_square(x) == x) then true else false
	in 
	List.filter f xs


let group (lst: 'a list) : ('a * 'a) list =
	let accum = ([], None, 0)
	in
	let f (elem) (sofar, prev, position) =
	match prev with 
	| None -> (sofar, Some elem, position + 1)
	| Some x -> ((elem, x)::sofar, None, position + 1)
	in
	if (List.length(lst) mod 2 != 0) 
	then raise (Invalid_argument "group: list is uneven")
	else match List.fold_right f lst accum with 
		 | (x, y, z) -> x 

let unzip (lst: ('a * 'b) list) : ('a list * 'b list) =
	let f (x,y) (t,z) = (x::t,y::z)
	in
	List.fold_right f lst ([],[]) 
