(* Part 1 *)

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

let rec size (tree: 'a tree): int =
  match tree with 
  | Leaf -> 0
  | Fork (left, v, right) -> size (left) + 1 + size (right)

let rec sum (tree: int tree): int = 
  match tree with
  | Leaf -> 0
  | Fork (left, v, right) -> sum left + v + sum right

let rec product (tree: int tree): int = 
  match tree with
  | Leaf -> 1
  | Fork (left, v, right) -> product left * v * product right

let rec char_count (tree: string tree): int = 
  match tree with 
  | Leaf -> 0
  | Fork (left, s, right) -> char_count left + String.length(s) + char_count right

let rec height (tree: 'a tree): int = 
  match tree with
  | Leaf -> 1
  | Fork (left, _, right) -> if (size left > size right) 
                             then 1 + height left
                             else 1 + height right

let rec perfect_balance (tree: 'a tree): bool =
  match tree with 
  | Leaf -> true 
  | Fork (left, _, right) -> if (height left = height right)
                             then true
                             else false

let maximum (tree: int tree): int option = raise (Failure "syntax error)")
  (*
  let rec maximun_left (t: int tree) : int option =
    match t with 
    | Leaf -> None
    | Fork (Leaf, val, Leaf) -> Some val
    | Fork (left, val, _) -> let (max: int option) : int option = maximun_left(left) in 
                             if (val > Option.get(max)) then Some val else Some (Option.get(max))
  in
  let rec maximun_right (t: int tree) : int option =
    match t with 
    | Leaf -> None
    | Fork (Leaf, val, Leaf) -> Some val
    | Fork (_, val, right) -> let (max: int option) : int option = maximun_right(right) in 
                              if (val > Option.get(max)) then Some val else Some (Option.get(max))
  in 
  if (maximun_left(tree) > maximun_right(tree)) 
  then maximun_left(tree) 
  else maximun_right(tree)
      *)
        