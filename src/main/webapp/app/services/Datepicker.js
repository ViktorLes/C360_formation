angular.module('Datepicker').factory('DatepickerService', [function () {
		
		return {
			build: function (){
				
				var disabled = function disabled(data) {
				        var date = data.date,
				        mode = data.mode;
				    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
				};
			
				var popup1 = {
		            opened: false
		        };
	    		
				return {
		    		dt : new Date(),
		            today : function(date) {
		                date = new Date();
		            },
		           
		            dateOptions : {
		                    dateDisabled: disabled,
		                    formatYear: 'yy',
		                    maxDate: new Date(2020, 5, 22),
		                    minDate: new Date(),
		                    startingDay: 1
		            },
		            open1 : function() {
		                popup1.opened = true;
		            },
		            altInputFormats : ['M!/d!/yyyy'],
		            popup1 : popup1
				};
			}
		};
}]);