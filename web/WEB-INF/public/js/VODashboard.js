var app = angular.module("dashboard", []);
app.controller("venueDisplay", function($scope) {
    $scope.myObj = {
        "Name" : "Alfreds Futterkiste",
        "Country" : "Germany",
        "City" : "Berlin"
    }
});