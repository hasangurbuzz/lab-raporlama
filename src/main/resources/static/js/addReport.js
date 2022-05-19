const date = new Date();
const currentDate = date.toISOString().substring(0, 10);
const dateInput = document.getElementById("input_date")
dateInput.value = currentDate