(ns joy-clojure1.core)

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
