function refreshFormAfterFillingField(form, inputName, error) {
    form.$error.required.splice(0, 1);
    if (error) {
        form[inputName].$error = error;
        form[inputName].$invalid = true;
    } else {
        form[inputName].$error = {};
        form[inputName].$invalid = false;
    }
    if (!form.$error.required[0]) {
        form.$error = {};
    }
}

function expectFormToBeFilled(form) {
    expect(form.$error.required).toBeUndefined();
    form.$invalid = false;
}



