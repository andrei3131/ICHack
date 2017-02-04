(ns tutorial.core)

(use 'overtone.live)

(definst sin-wave [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 0.4] 
    (* (env-gen (lin attack sustain release) 1 1 0 1 FREE)
       (sin-osc freq)
        vol))
(comment
   (sin-wave)
)

(definst saw-wave [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 0.4] 
    (* (env-gen (lin attack sustain release) 1 1 0 1 FREE)
       (saw freq)
        vol))
(comment
   (saw-wave)
)

(definst square-wave [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 0.4] 
    (* (env-gen (lin attack sustain release) 1 1 0 1 FREE)
       (lf-pulse:ar freq)
        vol))
(comment
   (square-wave)
)

(definst noisey [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 0.4] 
    (* (env-gen (lin attack sustain release) 1 1 0 1 FREE)
       (pink-noise) ; also have (white-noise) and others...
        vol))
(comment
   (noisey)
)

(definst triangle-wave [freq 440 attack 0.01 sustain 0.1 release 0.4 vol 0.4] 
    (* (env-gen (lin attack sustain release) 1 1 0 1 FREE)
       (lf-tri freq)
       vol))
(comment
   (triangle-wave)
)

(definst spooky-house [freq 440 width 0.2 attack 0.3 sustain 4 release 0.3 vol 0.4] 
    (* (env-gen (lin attack sustain release) 1 1 0 1 FREE)
       (sin-osc (+ freq (* 20 (lf-pulse:kr 0.5 0 width))))
        vol))
(comment
  (spooky-house)  
)

(defn -main
      "Sentient Note"
      []
      (saw-wave))

