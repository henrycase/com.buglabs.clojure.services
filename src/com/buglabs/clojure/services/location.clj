(ns com.buglabs.clojure.services.location
  (:gen-class)
  (:require [com.buglabs.clojure.services.core :as core])
  (:use [clojure.xml :only (parse)]
        [clojure.string :only (lower-case)]))

(defn get-location
  "Poll the bug's location service."
  []
  (let [location-xml (parse (str (core/service-url) "/Location"))]
    (doall (:content location-xml))))

(defn has-location-service?
  "Is the location service enabled?"
  []
  (if (> (count (filter #(= "location" %) (core/list-services))) 0)
    true
    false))

(defn have-fix?
  "Do we have a location fix?"
  (not
   (and (if (nil? (get-location))))))
