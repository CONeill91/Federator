<!DOCTYPE html>
<html lang="en" ng-app="FederatorApp">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="msaccotelli">
    <title>Federator</title>
    <link rel='stylesheet' href='/webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <script src="/webjars/angularjs/1.2.26/angular.min.js"></script>
    <script src="/resources/js/app.js"></script>
    <script src="/resources/js/services.js"></script>
    <script src="/resources/js/controllers.js"></script>
</head>

<body ng-controller="SearchController">
<div class="container-fluid">

    <div class="row">
        <div class="col-md-12">
            <h2>Search</h2>

            <form ng-submit="doSearch()">

                <input type="text" ng-model="query" placeholder="Search">

                <button type="submit" class="btn btn-lg btn-primary" ng-disabled="!query || inflight">
                    Search
                </button>

            </form>
        </div>
    </div>


    <div class="row">
        <div class="col-md-12">
            <span ng-show="!inflight && searched && results.length === 0">No results</span>
            <table class="table table-striped" ng-show="results.length > 0">
                <thead>
                <tr>
                    <th>Result</th>
                    <th>Summary</th>
                    <th>Source</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="result in results">
                    <td>
                        <a target="_blank" href="{{result.href}}" title="{{result.title}}">{{result.title}}</a>
                    </td>
                    <td>{{result.content}}</td>
                    <td>{{result.source}}</td>

                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
