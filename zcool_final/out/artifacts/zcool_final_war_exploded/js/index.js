function view() {
    window.location.href = "picture.do?operate=view";
}
function good() {
    window.location.href = "picture.do?operate=good";
}
function degree() {
    window.location.href = "picture.do?operate=degree";
}

function picture(name) {
    window.location.href = "picture.do?name=" + name + "&operate=show";
}