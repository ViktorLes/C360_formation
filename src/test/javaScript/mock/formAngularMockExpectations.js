function expectFormToBeComplete(form) {
    expect(form.$error.required).toBeUndefined();
}

function expectFormToHaveMissingFields(form, number){
    expect(form.$error.required.length).toBe(number);
}

function expectFormToBeValid(form) {
    expect(form.$error).toEqual({});
    expect(form.$invalid).toBeFalsy();
}

function expectFormToBeInvalid(form) {
    expect(form.$invalid).toBeTruthy();
}