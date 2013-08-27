(ns conway.web
  (:require [ring.util.response :refer [response content-type status]]
            [ring.middleware.params :refer [wrap-params]]
            [conway.core :as game]
            [clojure.string :as s]))

(defn handle-request [{:keys [query-params]}]
  (let [{steps "steps"
         n "n"} query-params
         n (Long/parseLong n)
         steps (Long/parseLong steps)]
    (-> (response (s/join "\n\n"
                          (map #(game/pprint-grid % n)
                               (take steps (iterate game/step (game/create-grid n game/live?))))))
        (content-type "text/plain"))))

(def handler
  (-> handle-request
      wrap-params))
