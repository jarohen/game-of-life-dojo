(defproject conway "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring "1.2.0"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.4"]]

  :plugins [[lein-ring "0.8.6"]]

  :ring {:handler conway.web/handler
         :nrepl {:start? true
                 :port 7888}
         :auto-reload? false}

  :aliases {"start" ["ring" "server-headless"]})
