/**
 * Created by msaccotelli on 10/13/2014.
 */

angular.module('FederatorApp.services', []).
    factory('SearchService', function ($http) {

        return {
            search: function (query) {
                return $http.post('search', query);
            },
            summarize: function (source, url) {
                return $http.get('summarize?s=' + encodeURIComponent(source) + '&u=' + encodeURIComponent(url));
            },
            //Written by Danny - store function will send the clicked link and the searched term to the store function in the federator controller
            store: function (link, query) {
                param = [link,query];
                return $http.post('store',param);
            }
        };
    });