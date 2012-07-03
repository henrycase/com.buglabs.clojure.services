(ns com.buglabs.clojure.services.core
  (:gen-class)
  (:use [clojure.xml :only (parse)]
        [clojure.string :only (lower-case)]))

(def bug-host (ref "127.0.0.1"))

(defn service-url
  "Get the URL to the web services."
  []
  (format "http://%s/service" @bug-host))

(defn set-target
  "Specify the UP address to the BUG."
  [target]
  (dosync
   (ref-set bug-host target)))

(defn list-services
  []
  (let [service-list (for [service (:content (parse (service-url)))]
                       (:name (:attrs service)))]
    (map #'lower-case service-list)))
