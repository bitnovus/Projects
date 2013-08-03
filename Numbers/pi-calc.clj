(def n-value (read-string
               (first *command-line-args* )))

(def A [])
(def init-value 2)
(def init-size (+ (* 10 (double (/ n-value  3)) 1)))

(defn regular-form [orig]
  (def result ())
  (conj result (last orig))
  (def q 0)
  (def i (int (+ init-size 1)))
  (def testing 1)
 (doseq [cur-elem (rseq orig)]
    ;(prn "i = " i)
    ;(prn "cur-elem" cur-elem)
    (def x (+ (* 10 cur-elem) (* q i))) 
    ;(prn "x = " x)
    (def mod-elem (mod x (- (* 2 i) 1)))
    ;(prn "mod-elem = " mod-elem)
    (def result (conj result mod-elem))
    (def q (int (/ x (- (* 2 i) 1))))
    ;(prn "q = " q)
    (def i (- i 1))
 )
  [result q]
  )

(doseq [i (range init-size)]
  (def A (conj A init-value)))

;(regular-form A)

;(println init-size)
;(println A)
;(println (count A))
;(println (first(regular-form A)))

(def nines 0)
(def predigit 0)

(doseq [j (range n-value)]
    (def combo (regular-form A))
    (def A (first combo))
    (def q (int (/ (last combo) 10)))
    (def A (conj (rest A) (mod (first A) 10)))

    (if (= 9 q) 
        ((def nines (+ nines 1))
            (if (= 10 q)
                (printf "%d" (+ predigit 1))
                (doseq [i (range nines)]
                    (printf "%d" 0)
                )
                (def predigit 0)
                (def nines 0)
            )
        )
        ((printf "%d" predigit)
            (def predigit q)
            (if-not (= 0 nines) 
                (doseq [i (range nines)]
                    (printf "%d" 9)

                )
            )
            (def nines 0)
        )
    )
)
printf "%d" predigit
