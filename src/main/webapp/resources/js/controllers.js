/**
 * Created by msaccotelli on 10/13/2014.
 */

angular.module('FederatorApp.controllers', []).
    controller('SearchController', function ($scope, SearchService) {
        $scope.results = [];

        $scope.doSearch = function () {
            var searchPromise = SearchService.search($scope.query);

            searchPromise.then(
                function (payload) {
                    $scope.results = payload.data['searchResults'];
                });
        };

    });
