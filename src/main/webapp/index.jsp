<!DOCTYPE html>
<html ng-app="FederatorApp">
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <script src="/webjars/angularjs/1.2.26/angular.min.js"></script>
    <script src="/resources/js/app.js"></script>
    <script src="/resources/js/services.js"></script>
    <script src="/resources/js/controllers.js"></script>
</head>

<body ng-controller="SearchController">
<div class="container">

    <h2>Search</h2>

    <div class="row">
        <div class="col-xs-4">
            <input type="text" ng-model="query" placeholder="Search">
        </div>

        <div class="col-xs-4">
            <button class="btn btn-success" ng-click="doSearch()">
                <span class="glyphicon"></span> Search
            </button>
        </div>
    </div>

    <table class="table table-striped" ng-show="results.length > 0">
        <thead>
        <tr>
            <th>Result</th>
            <th>Source</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="result in results">
            <td>{{ result.title }}</td>
            <td>{{ result.source }}</td>
            <td>{{ result.content }}</td>
            <td><a target="_blank" href="{{ result.href }}">Link</a></td>
        </tr>
        </tbody>
    </table>

</div>

</body>
</html>
