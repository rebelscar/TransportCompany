


export function sortList(dataList: any[], sortColumn: string, sortDirection: string) {
  dataList.sort((a, b) => {
    let value1 = a[sortColumn];
    if(isNaN(Number(value1))) value1 = value1.toLowerCase();

    let value2 = b[sortColumn];
    if(isNaN(Number(value2))) value2 = value2.toLowerCase();

    let comparison = 0;
    if (value1 > value2) {
      comparison = 1;
    } else if (value1 < value2) {
      comparison = -1;
    }
    return sortDirection === 'asc' ? comparison : comparison * -1;
  });
}
