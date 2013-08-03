;;; usage: clojure pi-calc.clj [number-of-digits]

(def n-value (read-string
               (first *command-line-args* )))

(def A [])
(def init-value 2)
(def init-size (+ (int (with-precision 1 :FLOOR(* 10  (/ n-value 3)))) 1))

(defn regular-form [orig]
  (def result ())
  (conj result (last orig))
  (def q 0)
  (def i (int init-size))
  (def testing 1)
 (doseq [cur-elem (reverse orig)]
    (def x (+ (* 10 cur-elem) (* q i)))
    (def mod-elem (mod x (- (* 2 i) 1)))
    (def result (conj result mod-elem))
    (def q (int (/ x (- (* 2 i) 1))))
    (def i (- i 1))
 )
  [result q]
  )

(doseq [i (range init-size)]
  (def A (conj A init-value)))

(def nines 0)
(def predigit 0)

(doseq [j (range n-value)]
    (def combo (regular-form A))
    (def A (first combo))
    (def A (conj (rest A) (mod (last combo) 10)))
    (def q (int (/ (last combo) 10)))

    (def state-check (cond 
        (= 9 q) "state1"
        (= 10 q) "state2"
        :else "state3"
    ))

    (if (= state-check "state1")
        (do 
            (def nines (+ nines 1))
        )
    )

    (if (= state-check "state2")
        ( do
            (printf "%d" (+ predigit 1))
            (doseq [i (range nines)]
                (printf "%d" 0)
            )
            (def predigit 0)
            (def nines 0)
        )
    )

    (if (= state-check "state3")
        ( do
            (printf "%d" predigit)
            (def predigit q)
            
            (if (not(zero? nines))
                ( do
                    (doseq [k (range nines)]
                        (printf "%d" 9)
                    )
                )
            )
            (def nines 0)
        )
    )
)
(printf "%d" predigit)
