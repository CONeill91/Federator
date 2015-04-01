/**
 * Created by msaccotelli on 10/13/2014.
 */

angular.module('FederatorApp.controllers', []).
    controller('SearchController', function ($scope, SearchService) {
        $scope.results = [];
        $scope.currentActivetab;
        $scope.searchLocations = [
            'Confluence',
            'Jira',
            'Mail:PL',
            'Mail:BC',
            'Mail:CC',
            'Mail:PC',
            'Intranet',
            'Wiki',
            'Sharepoint'
        ];

        $scope.countResultsForSource = function(results, source) {
            var count = 0;
            results.forEach(function(r) {
                if(r.source === source) {
                    count++;
                }
            });
            return count;
        }

        $scope.activateTab = function(currentActivetab) {
            $scope.activeTab = currentActivetab;
            console.log(currentActivetab);
            return currentActivetab;
        }

        $scope.filterResults = function(result){
            return !$scope.activeTab || result.source === $scope.activeTab;
        }

        $scope.trimMail = function(result){
             if(result.source === "Mail:PL" || result.source === "Mail:BC" || result.source === "Mail:CC" || result.source === "Mail:PC"){
                var index = result.title.lastIndexOf("]");
                if (index > 0) {
                    result.title = result.title.slice(index + 1, result.title.length);

                }
             }

             return result.title;
        }

        $scope.search = {
            searchIn: angular.copy($scope.searchLocations)
        };

        $scope.checkAll = function () {
            $scope.search.searchIn = angular.copy($scope.searchLocations);
        };

        $scope.uncheckAll = function () {
            $scope.search.searchIn = [];
        };

        $scope.doParallelSearch = function () {
            $scope.inflight = 0;
            $scope.results = [];

            angular.forEach($scope.search.searchIn, function (value) {
                var searchPromise = SearchService.search(
                    {
                        query: $scope.query,
                        searchIn: [value]
                    }
                );
                $scope.inflight++;

                searchPromise.then(
                    function (payload) {
                        $scope.results = $scope.results.concat(payload.data['searchResults']);
                        $scope.inflight--;
                        $scope.searched = true;
                    }, function () {
                        // decrease even in case of error
                        $scope.inflight--;
                    });
            });
        };

        $scope.doSummary = function (result) {
            var summaryPromise = SearchService.summarize(result.source, result.href);

            summaryPromise.then(
                function (payload) {
                    //console.log(payload);
                    $scope.summary = payload.data['content'];
                });
        };

        $scope.storeData = function(href){

            SearchService.store(href);

        };
        //written by danny sends link & query to store service
        $scope.storeLink = function(link,query) {
            // Remember the link href
            SearchService.store(link,query);

        };
    });
