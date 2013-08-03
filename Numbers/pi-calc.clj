(def n-value (read-string
               (first *command-line-args* )))

(def A [])
(def init-value 2)
(def init-size (+ (* 10 (int (with-precision 100 :FLOOR (/ n-value 3)))) 1))

(defn regular-form [orig]
  (def result ())
  (conj result (last orig))
  (def q 0)
  (def i (int (+ init-size 0)))
  (def testing 1)
 (doseq [cur-elem (reverse orig)]
    ;(prn "========")
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
    ;(prn "========")
 )
  ;(println "Return q = " q)
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
    (def A (conj (rest A) (mod (last combo) 10)))
    (def q (int (/ (last combo) 10)))

    ;(println "A =" A)
    ;(println "q =" q)
    ;(println "nines = " nines)
    ;(println "predigit = " predigit)

    (def state-check (cond 
        (= 9 q) "state1"
        (= 10 q) "state2"
        :else "state3"
    ))

    (if (= state-check "state1")
        (do 
            ;(println "state1")
            (def nines (+ nines 1))
        )
    )

    (if (= state-check "state2")
        ( do
            ;(println "state2")
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
            ;(println "state3")
            (printf "%d" predigit)
            (def predigit q)
            
            (if (not(zero? nines))
                ( do
                    ;(println nines)
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
