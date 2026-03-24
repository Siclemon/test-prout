async function fetchHTML(path = 'nav.html') {
    let fetchResponse = await fetch(path);
    let htmlString = await fetchResponse.text();
    return htmlString;
}