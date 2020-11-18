import {
    displaySingleCampus,
    clearChildren
} from "./displaySingleCampus.js"
import {
    deleteCampus
} from "./deleteCampus.js"


const displayHomeView = function (campuses) {
    const mainElement = document.createElement("main");
    mainElement.classList.add("main-content")
    const sectionElement = document.createElement("section");
    sectionElement.classList.add("campus-list");
    mainElement.appendChild(sectionElement);

    // let campusListHtml = "";
    console.log(campuses)
    console.log(document.q)
    campuses.forEach(campus => {
        let campusElement = document.createElement('div')
        campusElement.classList.add("campus");
        let campusLocationElement = document.createElement("h2");
        campusLocationElement.classList.add("campus-location")
        campusLocationElement.innerText = campus.location;
        campusLocationElement.addEventListener('click', () => displaySingleCampus(campus));
        let campusTechStackElement = document.createElement("h3");
        campusTechStackElement.classList.add("campus-tech-stack");
        campusTechStackElement.innerText = campus.techStack;

        let deleteButton = document.createElement("button");
        deleteButton.innerText = `Delete ${campus.location}`;
        deleteButton.addEventListener('click', () => deleteCampus(campus))
        campusElement.appendChild(campusLocationElement);
        campusElement.appendChild(campusTechStackElement);
        campusElement.appendChild(deleteButton)
        sectionElement.appendChild(campusElement);


    });
    const form = document.createElement("form")
    form.classList.add("new-campus-form")
    const locationInput = document.createElement("input")
    locationInput.classList.add("new-campus-location")
    locationInput.setAttribute("type", "text")
    locationInput.setAttribute("placeholder", "Location")
    const techStackInput = document.createElement("input");
    techStackInput.classList.add("new-campus-tech-stack")
    techStackInput.setAttribute("type", "text")
    techStackInput.setAttribute("placeholder", "Tech Stack")
    const submitCampus = document.createElement("button")
    submitCampus.classList.add("submit-campus")
    submitCampus.innerText = "Submit New Campus"
    form.appendChild(locationInput);
    form.appendChild(techStackInput);
    form.appendChild(submitCampus)
    sectionElement.appendChild(form);

    submitCampus.addEventListener('click', (clickEvent) => {
        clickEvent.preventDefault()
        clearChildren(mainElement)
        const campusJson = {
            "location": locationInput.value,
            "techStack": techStackInput.value
        }
        fetch("http://localhost:8080/api/campuses", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(campusJson)
            })
            .then(response => response.json())
            .then(campuses => displayHomeView(campuses))
            .then(campusesElement => mainElement.appendChild(campusesElement))
            .catch(err => console.error(err));
    })

    return mainElement;
}


export {
    displayHomeView
}