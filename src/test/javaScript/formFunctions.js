function refreshFormAfterFillingField(form, inputName, error) {
    form.$error.required.splice(0,1);
    if(error) {
        form[inputName].$error = error;
        form[inputName].$invalid = true;
    } else {
        form[inputName].$error = {};
        form[inputName].$invalid = false;
    }
    if(!form.$error.required[0]) {
        form.$error = {};
    }
}

function expectFormToBeFilled(form) {
    expect(form.$error.required).toBeUndefined();
    form.$invalid = false;
}


function Form(){

    var form = {
        $error: {},
        $invalid: false,
        $valid: true,
        inputs: [],
        inputsMessages: []
    };
    form.createInput = function(name, obj){
        var input = {
            type: 'text',
            'ng-pattern': null,
            'ng-focus' : null,
            'ng-change' : null,
            'ng-blur' : null,
            required: false,
            'ng-model' : null,
            initialValue : "",
            $invalid : false,
            $valid : true,
            $error : {}
        };
        for(var property in obj) {
            if(input[property] !== undefined){
                input[property] = obj[property];
            }
        }
        inputs[name] = input;
    };

    return form;
}

