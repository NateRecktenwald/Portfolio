
Problem 1: a property for prod

	let rec prod (lst: int list) : int = match lst with
		| [] -> 1
		| y::ys -> y * prod ys

	prod (l1 @ l2) = prod l1 * prod l2

	Base Case: l1 = []
		P([] @ l2): prod ([] @ l2) = prod [] * prod l2

		  prod ([] @ l2)
		= prod l2				by properties of list and append
		= 1 * prod l2			by properties of integers and multiplication
		= prod [] * prod l2		by definition of prod

	Inductive Case: l1 = x::xs
		P((x::xs) @ l2): prod ((x::xs) @ l2) = prod (x::xs) * prod l2
		Given: P(xs): prod (xs @ l2) = prod xs * prod l2

		  prod ((x::xs) @ l2)
		= prod (x :: (xs @ l2))		by properties of lists
		= x * prod (xs @ l2)		by definition of prod
		= x * prod xs * prod l2		by the inductive hypothesis
		= prod (x::xs) * prod l2    by definition of prod




Problem 2: product 2 ways

	let rec prod (lst: int list) : int = match lst with
		| [] -> 1
		| y::ys -> y * prod ys

	let rec foldr (f: 'a -> 'b -> 'b) (lst: 'a list) (base: 'b) : 'b =
		match lst with
		| [] -> base
		| x::xs -> f x (foldr f xs base)

	let prod_fold (lst: int list) : int = foldr ( * ) lst 1

	prod lst = prod_fold lst

	Base Case: lst = []
		P([]): prod [] = prod_fold []

		 prod []
		= 1							by definition of prod


		  prod_fold [] 
		= foldr ( * ) [] 1			by definition of prod_fold
		= 1 						by definition of foldr 

		Since both equal 1 then both are equal since they evaluate to the same number therefore prooving 
		the base case

	Inductive Case: lst = x::xs
		P(x::xs): prod (x::xs) = prod_fold (x::xs)
		Given: prod xs = prod_fold xs

		  prod (x::xs)
		= x * prod xs 				by definition of prod
		= x * prod_fold xs      	by the inductive hypothesis
		= x * (foldr ( * ) xs 1) 	by the definition of prod_fold
		= foldr ( * ) (x::xs) 1 	by the definition of foldr where * is f and x is x
		= prod_fold (x::xs) 		by the definition of prod_fold  




Problem 3: maximum of natural numbers

	type nat = Zero | Succ of nat

	let rec maximum (lst: nat list) : nat = match lst with
		| [] -> Zero
		| n::ns -> maxnat n (maximum ns)

	maxnat Zero n = n

	maxnat a (maxnat b c) = maxnat (maxnat a b) c

	Base Case: l1 = []
		P([]): Show: maximum ([] @ l2) = maxnat (maximum []) (maximum l2)

		  maximum ([] @ l2)
		= maximum l2						by properties of lists and append

		  maxnat (maximum []) (maximum l2)
		= maxnat Zero (maximum l2) 			by the definition of maximum
		= maximum l2 						by the property given about maxnat

		Since both equal the maximum of l2 that means the two are equal 

	Inductive Case: l1 = x::xs
		P(x::xs): Show: maximum ((x::xs) @ l2) = maxnat (maximum (x::xs)) (maximum l2)
		Given: maximum (xs @ l2) = maxnat (maximum xs) (maximum l2)

		  maximum ((x::xs) @ l2)
		= maximum (x::(xs @ l2)) 						by properties of lists, append, and cons
		= maxnat x (maximum (xs @ l2))					by the definition of maximum
		= maxnat x (maxnat (maximum xs) (maximum l2))   by the inductive hypothesis
		= maxnat (maxnat x (maximum xs)) (maximum l2) 	by the property given about maxnat
		  maxnat (maximum (x::xs)) (maximum l2)			by the definition of maximum




Problem 4: Principles of Induction

	Part 1: 
		
		type 'a tree 
		= Leaf 
		| Fork of 'a tree * 'a * 'a tree  

		for all t: 'a tree, P(t) holds if
		- P(Leaf)
		- P(t1), and P(t2) implies P(Fork(t1, v, t2)) (for any v of type 'a)

	Part 2: 
		
		type 'a rose_tree = Rose of 'a * 'a rose_tree list

		for all t: 'a rose_tree, P(t) holds if 
		- P(v []) satisfies p for any v of 'a where [] is the list of rose trees
		- P(l) where l is a list of rose trees implies P(Rose(v l)) where v is a 'a value

	Part 3:

		type 'a elf_tree = Empty
						 | Leaf of 'a 
						 | Fork of 'a * 'a elf_tree * 'a elf_tree

		for all t: 'a elf_tree, P(t) holds if
		- P(Empty)
		- P(Leaf v) satisfies p for any v of type 'a
		- P(t1), and P(t2) implies P(Fork(v, t1, t2)) (for any v of type 'a)




Problem 5:


	let rec height_rec (t: 'a elf_tree) : int = match t with 
		| Empty -> 0
		| Leaf _ -> 1 
		| Fork (_, l, r) -> 1 + max (height_rec l) (height_rec r)
	
	let height (tree: 'a elf_tree): int = 
	  match tree with 
	  | Empty -> 0
	  | Leaf v -> 1
	  | Fork (v, left, right) -> 1 + max
	                             (reduce 0 (fun elem -> 1) (fun v left right -> left + right) left)
	                             (reduce 0 (fun elem -> 1) (fun v left right -> right + left) right)

	let rec reduce (base: 'b) (func2: 'a -> 'b) (func: 'a -> 'b -> 'b -> 'b) (tree: 'a elf_tree) : 'b = 
	  match tree with
	  | Empty -> base
	  | Leaf v -> func2 v 
	  | Fork (v, left, right) -> func v (reduce base func2 func left) (reduce base func2 func right)

	Base Case: P(Empty): Show height_rec Empty = height Empty
		
		  height_rec Empty 
		= 0 						by thr definition of height_rec
		= height Empty				by the defninition of height

	Leaf Case: P(Leaf v): Show height_rec (Leaf v) = height (Leaf v)
		
		  height_rec (Leaf v)
		= 1							by the definition of height_rec
		= height (Leaf v) 			by the definition of height

	Inductive Case: P(Fork(v, l, r)): Show height_rec (Fork(v, l, r)) = height (Fork(v, l, r))
					Given: height_rec l = height l
						   height_rec r = height r

		  height_rec (Fork(v, l, r)) 
		= 1 + max (height_rec l) (height_rec r)		by the definition of height_rec
		= fun _ l r -> 1 + max l r) (reduce 0 (fun x -> 1) (fun _ l r -> 1 + max l r) l) 
									(reduce 0 (fun x -> 1) (fun _ l r -> 1 + max l r) r)
									(Fork(_, l, r))					by properties of functions
									
		= reduce 0 (fun x -> 1) (fun _ l r -> 1 + max l r) (Fork(_, l, r)) 		by the definition of reduce
		= height (Fork(v, l, r))		by the definition of height
