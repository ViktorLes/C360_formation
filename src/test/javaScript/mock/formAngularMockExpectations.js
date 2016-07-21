function expectFormToBeComplete(form) {
    expect(form.$error.required).toBeUndefined();
}

function expectFormToHaveMissingFields(form, numberOfMissingFields){
    expect(form.$error.required.length).toBe(numberOfMissingFields);
}

function expectFormToBeValid(form) {
    expect(form.$error).toEqual({});
    expect(form.$invalid).toBeFalsy();
}

function expectFormToBeInvalid(form) {
    expect(form.$invalid).toBeTruthy();
}