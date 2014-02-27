'use strict';

eventsApp.controller('EventController',
//populates the scope with the elements that will be needed by the view
function EventController($scope)
{
    //Add an 'event' object to the scope and populate values
    $scope.event={
        name: 'Angular boot Camp',
        otherMsg: 'Yadda',
        childObj: {
            someProp: 'Ujuuu'
        },
        bassetImg: 'img/basset.jpg'
    }
}
);
