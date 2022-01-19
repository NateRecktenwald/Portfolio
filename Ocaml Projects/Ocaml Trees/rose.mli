(* Part 3: an interface for rose.ml *)

type 'a rose_tree 

val t1 : int rose_tree
val t2 : string rose_tree
val t3 : char rose_tree
val t4 : int rose_tree

val size : 'a rose_tree -> int 

val sum : int rose_tree -> int

val product : int rose_tree -> int

val char_count : string rose_tree -> int

val height : 'a rose_tree -> int

val perfect_balance : 'a rose_tree -> bool

val maximum : int rose_tree -> int option
