import {
    allCampuses
} from "./sampleAllCampusJson.js"
import {
    displayHomeView
} from "./displayHomeView.js"
const displaySingleCampus = function (campus) {
    const mainContent = document.querySelector(".main-content")
    clearChildren(mainContent);
    const campusLibraryElement = document.createElement("section");
    campusLibraryElement.classList.add("campus-library");
    mainContent.appendChild(campusLibraryElement);

    const libraryHeader = document.createElement("header");

    const libraryLocationElement = document.createElement("h2");
    libraryLocationElement.innerText = campus.location;
    libraryLocationElement.classList.add("campus-library-header__location")

    const libraryTechStack = document.createElement("h3");
    libraryTechStack.innerText = campus.techStack;
    libraryTechStack.classList.add("campus-library-header__tech-stack")

    libraryHeader.appendChild(libraryLocationElement);
    libraryHeader.appendChild(libraryTechStack);
    campusLibraryElement.appendChild(libraryHeader);

    const booksElement = document.createElement("section");
    booksElement.classList.add("campus-books");
    campusLibraryElement.appendChild(booksElement);

    campus.books.forEach(book => {
        const bookTitle = document.createElement("h3");
        bookTitle.classList.add("book-title");
        bookTitle.innerText = book.title;
        booksElement.appendChild(bookTitle);
        bookTitle.addEventListener('click', () => alert(`This book's summary: ${book.summary}`))
    });

    const backToAllCampuses = document.createElement("a");
    backToAllCampuses.classList.add("back-navigation");
    backToAllCampuses.innerText = "back to campus listings"

    // backToAllCampuses.addEventListener('click', clickEvent => {
    //     clearChildren(mainContent);
    //     mainContent.appendChild(displayHomeView(allCampuses));
    // });

    backToAllCampuses.addEventListener('click', () => {
        clearChildren(mainContent);
        fetch("http://localhost:8080/api/campuses")
            .then(response => response.json())
            .then(campuses => displayHomeView(campuses))
            .then(campusesElement => mainContent.appendChild(campusesElement))
            .catch(error => console.log(error));
    });

    campusLibraryElement.appendChild(backToAllCampuses);

}

const clearChildren = function (element) {
    while (element.firstChild) {
        element.removeChild(element.lastChild);
    }
}

export {
    displaySingleCampus,
    clearChildren
}