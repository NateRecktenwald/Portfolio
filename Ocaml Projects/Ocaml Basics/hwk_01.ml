let rec sum (xs : int list) : int =
	match xs with 
	| [] -> 0
	| x::rest -> x + sum rest

let rec sumf (xs : float list) : float =
	match xs with
	| [] -> 0.0
	| x::rest -> x +. sumf rest

let rec product (xs : int list) : int =
	match xs with 
	| [] -> 1
	| x:: rest -> x * product rest

let rec productf (xs : float list) : float =
	match xs with 
	| [] -> 1.0
	| x::rest -> x *. productf rest

let rec minimum (a: 'a list) : 'a =
	match a with 
	| [] -> raise (Invalid_argument "minimum: the list is empty")
	|x::[] -> x
	| x::rest -> 
		let (y: 'a) : 'a = minimum(rest) in 
			if (x < y) then x else y

let rec longest (ss: string list) : string =
	match ss with 
	| [] -> raise (Invalid_argument "longest: the list is empty")
	| s::[] -> s
	| s::y::rest -> if (String.length(s) > String.length(y)) then longest(s::rest) else longest(y::rest)
		
let rec append (x: 'a list) (y: 'a list) : 'a list =
	match x with 
	| [] -> y
	| z::rest -> z::(append rest y)

let rec elem (x: 'a) (lst: 'a list) : bool = 
	match x,lst with 
	| x,[] -> false
	| x,z::rest -> (z = x) || (elem x rest)

let rec excited (x: string list) : string list =
	match x with 
	| [] -> []
	| y::rest -> (y^"!")::excited(rest)

let rec suffix (x: string) (xs: string list) : string list = 
	match xs with
	| [] -> []
	| y::rest -> (y^x)::(suffix x rest)

let excited' (lst: string list) : string list =
	suffix "!" lst

let rec lengths (lst: string list) : int list =
	match lst with
	| [] -> []
	| x::rest -> String.length(x)::lengths(rest)

let rec length_pairs (lst: string list) : (string * int) list = 
	match lst with
	| [] -> []
	| x::rest -> (x, String.length(x))::length_pairs(rest)

let rec capitalize (lst: string list) : string list =
	match lst with 
	| [] -> []
	| x::rest -> String.capitalize_ascii(x)::capitalize(rest)

let rec all_odds (lst: int list) : int list = 
	match lst with 
	| [] -> []
	| x::rest -> if ((x mod 2) = 0) then all_odds(rest) else x::all_odds(rest)

let rec all_capitalized (lst: string list) : string list =
	match lst with 
	| [] -> []
	| x::rest -> 
		if (x = "") then raise (Invalid_argument "all_capitalized: Empty string") else 
			if (x.[0] = Char.uppercase_ascii(x.[0])) then (x::(all_capitalized rest)) else (all_capitalized(rest)) 


let rec check_square (x: int) : int =
	if x = 0 then 0 else 
		let n = check_square (x - 1) in 
			if ((n+1)*(n+1) <= x) then n+1 else n

	 
let rec all_squares (xs: int list) : int list =
	match xs with 
	| [] -> []
	| x::rest -> if (check_square(x) * check_square(x) == x) then x::all_squares(rest) else all_squares(rest)


let rec group (lst: 'a list) : ('a * 'a) list =
	match lst with 
	| [] -> []
	| x::[] -> raise (Invalid_argument "group: list is uneven")
	| x::y::rest -> (x, y)::group(rest)

let rec unzip (lst: ('a * 'b) list) : ('a list * 'b list) =
	match lst with
	| [] -> ([],[])
	| (x,y)::rest -> 
		let (one, two) = unzip rest in
			(x::one, y::two)
