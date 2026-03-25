async function fetchHTML(path = 'farwest.html') {
    let fetchResponse = await fetch(path);
    let htmlString = await fetchResponse.text();
    return htmlString;
}