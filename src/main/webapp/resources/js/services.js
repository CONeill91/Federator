/**
 * Created by msaccotelli on 10/13/2014.
 */

angular.module('FederatorApp.services', []).
    factory('SearchService', function ($http) {

        return {
            search: function (query) {
                return $http.get('/search?q=' + encodeURIComponent(query));
            }
        };
    });
