(ns joy-clojure.core)

(defn r->lfix
  ([a op b] (op a b))
  ([a op1 b op2 c] (op1 a (op2 b c)))
  ([a op1 b op2 c op3 d] (op1 a (op2 b (op3 c d)))))

(r->lfix 10 * 2 + 3)
;; => 50
(defn l->rfix
  ([a op b] (op a b))
  ([a op1 b op2 c] (op2 c (op1 a b)))
  ([a op1 b op2 c op3 d] (op3 d (op2 c (op1 a b)))))

(l->rfix 10 * 2 + 3)
;; => 23
(l->rfix 1 + 2 + 3)
;; => 6
(l->rfix 1 + 2 * 3)
;; => 9 incorrect

;; map to define order of operations

(def order {+ 0 - 0
            * 1 / 1})

(defn infix3 [a op1 b op2 c]
  (if (< (get order op1) (get order op2))
    (r->lfix a op1 b op2 c)
    (l->rfix a op1 b op2 c)))

(infix3 1 + 2 * 3) ;; => 7
(infix3 10 * 2 + 3) ;; => 23

(< (+ 1 (* 2 3) (* 2 (+ 1 3))))

(sort < [2 5 4 1 6 3 7])
(sort > [2 5 4 1 6 3 7])

;; (defprotocol Concatenable
;;   (cat [this other]))

;; (extend-type String
;;   Concatenable
;;   (cat [this other]
;;     (.concat this other)))

;; (cat "House " " of lies")

;; (extend-type java.util.List
;;   Concatenable
;;   (cat [this other]
;;     (concat this other)))

;; (cat [1 2 3] [4 5 6])



(def make-list #(list %&))

(make-list 1 2 3 4 5)

;; use do when you have a series  of expressions that need to be treated as one
(do
  (def x 5)
  (def y 4)
  (+ x y) ; addition is executed but thrown away...
  [x y])  ; only the final expression is returned
  ;; => [5 4]

;; LOCALS

(let [ r       5
      pi       3.1415
      r-sq (* r r) ]
  (println "radius is" r)
  (* pi r-sq))

; LOOPS
(defn print-down-from [x]
  (when (pos? x)
    (println x)
    (recur (dec x))))

(print-down-from 99)

;; when is used above. generally when should be used in the following cases:
;; 1. no `else` part is associated with the result of a conditional
;; 2. you require an implicit `do` in order to perform side effects

(defn sum-down-from [sum x]
  (if (pos? x)
    (recur (+ sum x) (dec x))
    sum))

(sum-down-from 0 10)
