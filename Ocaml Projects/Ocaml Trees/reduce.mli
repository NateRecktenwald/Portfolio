(* Part 2: an interface for reduce.ml *)

type 'a tree 

val t1 : int tree
val t2 : string tree
val t3 : int tree 
val t4 : 'a tree

val size : 'a tree -> int 

val sum : int tree -> int

val product : int tree -> int

val char_count : string tree -> int

val height : 'a tree -> int

val perfect_balance : 'a tree -> bool

val maximum : int tree -> int option
