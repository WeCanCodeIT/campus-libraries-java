import {
    createHeader
} from "./js/Header.js"
import {
    displayHomeView
} from "./js/displayHomeView.js"
import {
    createFooter
} from "./js/Footer.js"

const container = document.querySelector('.container');

container.prepend(createHeader());
const mainElement = document.createElement("main");
mainElement.classList.add("main-content");
container.appendChild(mainElement)

fetch("http://localhost:8080/api/campuses")
    .then(response => response.json())
    .then(campuses => displayHomeView(campuses))
    .then(campusesElement => mainElement.appendChild(campusesElement))
    .catch(error => console.log(error));

container.appendChild(createFooter());