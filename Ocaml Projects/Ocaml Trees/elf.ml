(* Part 4 *)

type 'a elf_tree = Empty
                 | Leaf of 'a
                 | Fork of 'a * 'a elf_tree * 'a elf_tree

let t1 : int elf_tree = 
  Fork (3,
        Fork (2, Empty, Leaf 6),
        Fork (4, 
              Leaf 7,
              Fork (5, Empty, Empty)
          )
    )

let t2 : string elf_tree = 
  Fork ("12",
        Fork ("34", Empty, Leaf "567"),
        Fork ("8910", 
              Leaf "1112",
              Fork ("13", Empty, Empty)
          )
    )

let t3 : int elf_tree =
  Fork (1,
        Fork (2, 
              Fork (3, Leaf 0, Leaf 0),
              Fork (4, Leaf 0, Leaf 0) ),
        Fork (5, 
              Fork (6, Leaf 0, Leaf 0),
              Fork (7, Leaf 0, Leaf 0) )
    )

let t4 : 'a elf_tree = Empty

let rec reduce (base: 'b) (func2: 'a -> 'b) (func: 'a -> 'b -> 'b -> 'b) (tree: 'a elf_tree) : 'b = 
  match tree with
  | Empty -> base
  | Leaf v -> func2 v 
  | Fork (v, left, right) -> func v (reduce base func2 func left) (reduce base func2 func right)

let size (tree: 'a elf_tree): int = 
  reduce 0 (fun elem -> 1) (fun v left right -> left + 1 + right) tree

let sum (tree: int elf_tree): int = 
  reduce 0 (fun elem -> elem) (fun v left right -> left + v + right) tree 

let product (tree: int elf_tree): int = 
  reduce 1 (fun elem -> elem) (fun v left right -> left * v * right) tree 

let char_count (tree: string elf_tree): int = 
  reduce 0 (fun elem -> String.length(elem)) (fun v left right -> left + String.length(v) + right) tree 

let height (tree: 'a elf_tree): int = 
  match tree with 
  | Empty -> 0
  | Leaf v -> 1
  | Fork (v, left, right) -> if (size left > size right) 
                             then reduce 0 (fun elem -> 1) (fun v left right -> left + 1) tree
                             else reduce 0 (fun elem -> 1) (fun v left right -> right + 1) tree

let perfect_balance (tree: 'a elf_tree): bool = 
  let helper (t: 'a elf_tree) =
    match t with 
    | Empty -> (false, 0)
    | Leaf v -> (true,1)
    | Fork (v, left, right) -> reduce (true, 1) (fun elem -> (true, 1)) (fun _ l r -> 
              match l with 
              | (isball, sizel) ->
              match r with
              | (isbalr, sizer) -> if (sizel = sizer && isball && isbalr) 
                                   then (true, sizel + sizer)
                                   else (false, 0)
             ) tree 
   in
   match helper(tree) with 
   | (isbal, size) -> isbal


let maximum (tree: int elf_tree): int option = 
    let helper (value: int) (basel: int option) (baser: int option): int option =   (* need to finish *)
    match (basel,baser) with 
    | (None, None) -> Some value
    | (Some l, None) -> Some (max l value)
    | (None, Some r) -> Some (max r value)
    | (Some l, Some r) -> Some (max l (max r value))
  in 
  reduce None (fun elem -> Some elem) helper tree

