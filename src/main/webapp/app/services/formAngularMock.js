function Input(){
    this.$invalid = false;
    this.$valid = true;
    this.$error = {};
}

function Form(){
    var form = this;
    form.$error = {};
    form.$invalid = false;
    form.$valid = true;
}

function InputHTML() {
    var inputHTML = this;
    inputHTML.name = "";
    inputHTML.type = 'text';
    inputHTML['ng-pattern'] = null;
    inputHTML['ng-focus'] = null;
    inputHTML['ng-change'] = null;
    inputHTML['ng-blur'] = null;
    inputHTML['ng-minlength'] = null;
    inputHTML['ng-maxlength'] = null;
    inputHTML.required = false;
    inputHTML.min = null;
    inputHTML.max = null;
    inputHTML.model = null;
    inputHTML.initialValue = "";
    inputHTML.input = new Input;
    var input = inputHTML.input;

    inputHTML.setProperties = function(obj){
        for (var property in obj) {
            if (inputHTML[property] !== undefined) {
                inputHTML[property] = obj[property];
            }
        }
        if(inputHTML.name && inputHTML.model && inputHTML.initialValue) {
            inputHTML.model[inputHTML.name] = inputHTML.initialValue;
        }
    };

    inputHTML.makeValid = function(){
        inputHTML.input.$invalid = false;
        inputHTML.input.$valid = true;
        inputHTML.input.$error = {};
    };

    inputHTML.makeInvalid = function(){
        inputHTML.input.$invalid = true;
        inputHTML.input.$valid = false;
    };

    function actualiseStatesInputTypeText(){
        if(inputHTML['ng-minlength'] && value.length < inputHTML['ng-minlength']){
            inputHTML.input.$error.minlength = true;
            inputHTML.makeInvalid();
        }
        if(inputHTML['ng-maxlength'] && value.length > inputHTML['ng-maxlength']){
            inputHTML.input.$error.maxlength = true;
            inputHTML.makeInvalid();
        }
    };

    function actualiseStatesInputTypeNumber(){
        if(inputHTML.min && value < inputHTML.min){
            inputHTML.input.$error.min = true;
            inputHTML.makeInvalid();
        }
        if(inputHTML.max && value > inputHTML.max){
            inputHTML.input.$error.max = true;
            inputHTML.makeInvalid();
        }
    }

    inputHTML.actualiseStatesInput = function(){
        inputHTML.makeValid();
        if(inputHTML.required && !inputHTML.model[inputHTML.name]) {
            inputHTML.input.$error.required = true;
            inputHTML.makeInvalid();
        }else {
            value = inputHTML.model[inputHTML.name];
            if(inputHTML['ng-pattern'] && !inputHTML['ng-pattern'].test(value)) {
                inputHTML.input.$error.pattern = true;
                inputHTML.makeInvalid();
            }
            if(inputHTML.type === 'text'){
                actualiseStatesInputTypeText();
            }else if(inputHTML.type === 'number'){
                actualiseStatesInputTypeNumber();
            }
        }
    };
}

function FormHTML() {
    var formHTML = this;
    formHTML.inputs = [];
    formHTML.inputsMessages = [];
    formHTML.form = new Form;
    var form = formHTML.form;

    formHTML.actualiseStatesForm = function(){
        form.$error = {};
        form.$invalid = false;
        form.$valid = true;
        for(var inputName in formHTML.inputs){
            var input = formHTML.inputs[inputName].input;
            if(form.$valid && input.$invalid){
                form.$invalid = true;
                form.$valid = false;
            }
            for(var error in input.$error){
                if(input.$error[error]){
                    if(!Array.isArray(form.$error[error])){
                        form.$error[error] = [];
                    }
                    form.$error[error].push({});
                }
            }
        }
    };

    formHTML.createInputHTML = function(obj) {
        var inputHTML = new InputHTML;
        if(!obj.name) throw {message: "property 'name' of InputHTML is not defined"};
        if(!obj.model) throw {message: "property 'model' of InputHTML '"+obj.name+"' is not defined"};
        inputHTML.setProperties(obj);
        formHTML.inputs[inputHTML.name] = inputHTML;
        formHTML.form[inputHTML.name] = inputHTML.input;

        inputHTML.setValue = function(value){
            inputHTML.model[inputHTML.name] = value;
            inputHTML.actualiseStatesInput();
            formHTML.actualiseStatesForm();
            //change states of messages

        };
    };
}
