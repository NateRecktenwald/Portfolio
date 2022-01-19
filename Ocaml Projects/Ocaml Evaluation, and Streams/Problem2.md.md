# Homework 7: Lazy Evaluation

by Nathan Recktenwald 


foldr f [] v = v
foldr f (x::xs) v = f x (foldr f xs v)

foldl f v [] = v
foldl f v (x::xs) = foldl f (f v x) xs

and b1 b2 = if b1 then b2 else false

andl l = foldl and true l
andr l = foldr and l true


## andr - Call-by-value

  andr (true::false::true::true::[])	
= foldr and (true::false::true::true::[]) true  	
= and true (foldr and (false::true::true::[]) true)	 
= and true (and false (foldr and (true::true::[]) true))	
= and true (and false (and true (foldr and (true::[]) true)))	
= and true (and false (and true (and true (foldr and [] true))))	
= and true (and false (and true (and true true)))	
= and true (and false (and true (if true then true else false)))	
= and true (and false (and true true))	
= and true (and false (if true then true else false))	
= and true (and false true)  
= and true (if false then true else false)	
= and true false  
= if true then false else true  
= false  


## andr - call-by-name

  andr (true::false::true::true::[])  
= foldr and (true::false::true::true::[]) true  
= and true (foldr and (false::true::true::[]) true)  
= if true then (foldr and (false::true::true::[]) true)  
= foldr and (false::true::true::[]) true  
= and false (foldr and (true::true::[]) true)  
= if false then (foldr and (true::true::[]) true) else false  
= false   


## andl - call-by-value

  andl (true::false::true::true::[])  
= foldl and (true::false::true::true::[]) true  
= foldl and (and true true) (false::true::true::[])  
= foldl and (and false (and true true)) (true::true::[])  
= foldl and (and true (and false (and true true))) (true::[])  
= foldl and (and true (and true (and false (and true true)))) []  
= and true (and true (and false (and true true)))  
= and true (and true (and false (if true then true else false)))  
= and true (and true (and false true))  
= and true (and true (if false then true else false))  
= and true (and true false)  
= and true (if true then false else false)  
= and true false  
= if true then false else false  
= false  


## andl - call-by-name

  andl (true::false::true::true::[])  
= foldl and (true::false::true::true::[]) true  
= foldl and (and true true) (false::true::true::[])  
= foldl and (if true then true else) (false::true::true::[])  
= foldl and true (false::true::true::[])  
= foldl and (and true false) (true::true::[])  
= foldl and (if true then false else false) (true::true::[])  
= foldl and false (true::true::[])   
= foldl and (and false true) (true::[])  
= foldl and (if false then true else) (true::[])  
= foldl and false (true::[])  
= foldl and (and false true) []  
= foldl and (if false then true else false)  
= foldl and false []  
= false  
