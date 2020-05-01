(ns joy-clojure1.chess)

(defn initial-board []
  [\r \n \b \q \k \b \n \r
   \p \p \p \p \p \p \p \p
   \- \- \- \- \- \- \- \-
   \- \- \- \- \- \- \- \-
   \- \- \- \- \- \- \- \-
   \- \- \- \- \- \- \- \-
   \P \P \P \P \P \P \P \P
   \R \N \B \Q \K \B \N \R])

(def ^:dynamic *file-key* \a)
(def ^:dynamic *rank-key* \0)

 ;; calculate the "file" (horizontal) position
(defn- file-component [file]
  (- (int file) (int *file-key*)))

;; calculate the "rank" (vertical) position
(defn- rank-component [rank]
  (->> (int *rank-key*)
       (- (int rank))
       (- 8)
       (* 8)))

;; project the ID layout onto a logical 2D chess board
(defn- index [file rank]
  (+ (file-component file) (rank-component rank)))

(defn- lookup [board pos]
  (let [[file rank] pos]
    (board (index file rank))))

(lookup (initial-board) "a1")
