(* Part 4: an interface for elf.mli *)

type 'a elf_tree 

val t1 : int elf_tree
val t2 : string elf_tree
val t3 : int elf_tree 
val t4 : 'a elf_tree

val size : 'a elf_tree -> int 

val sum : int elf_tree -> int

val product : int elf_tree -> int

val char_count : string elf_tree -> int

val height : 'a elf_tree -> int

val perfect_balance : 'a elf_tree -> bool

val maximum : int elf_tree -> int option
