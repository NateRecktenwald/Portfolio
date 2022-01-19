(* Part 2 *)

type 'a tree 
  = Leaf 
  | Fork of 'a tree * 'a * 'a tree

let t1 : int tree = 
  Fork (Fork (Leaf, 2, Leaf),
        3,
        Fork (Leaf,
              4, 
              Fork (Leaf, 5, Leaf)
          )
    )

let t2 : string tree = 
  Fork (Fork (Leaf, "12", Leaf),
        "34",
        Fork (Leaf,
              "5", 
              Fork (Leaf, "678", Leaf)
          )
    )

let t3 : int tree =
  Fork (Fork (Fork (Leaf, 1, Leaf),
              2, 
              Fork (Leaf, 3, Leaf) ),
        4,
        Fork (Fork (Leaf, 5, Leaf),
              6, 
              Fork (Leaf, 7, Leaf) )
    )

let t4 : 'a tree = Leaf

let rec reduce (base: 'b) (func: 'b -> 'a -> 'b -> 'b) (tree: 'a tree) : 'b = 
  match tree with
  | Leaf -> base
  | Fork (left, v, right) -> func (reduce base func left) v (reduce base func right)

let size (tree: 'a tree): int =
  reduce 0 (fun left v right -> left + 1 + right) tree  

let sum (tree: int tree): int =
  reduce 0 (fun left v right -> left + v + right) tree 

let product (tree: int tree): int =
  reduce 1 (fun left v right -> left * v * right) tree 

let char_count (tree: string tree): int = 
  reduce 0 (fun left s right -> left + String.length(s) + right) tree

let height (tree: 'a tree): int =
  match tree with 
  | Leaf -> 0
  | Fork (left, v, right) -> if (size left > size right) 
                             then reduce 1 (fun left v right -> left + 1) tree
                             else reduce 1 (fun left v right -> right + 1) tree

let perfect_balance (tree: 'a tree) =
 let helper (t: 'a tree) =
  match t with 
  | Leaf -> (true,1)
  | Fork (left, v, right) -> reduce (true, 1) (fun l _ r -> 
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
                         


let maximum (tree: int tree): int option = 
  let helper (basel: int option) (value: int) (baser: int option) = 
    match (basel,baser) with 
    | (None, None) -> Some value
    | (Some l, None) -> Some (max l value) 
    | (None, Some r) -> Some (max r value)
    | (Some l, Some r) -> Some (max l (max r value))
  in 
  reduce None helper tree
