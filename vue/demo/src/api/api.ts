import axios from 'axios'

export async function getBeauty(url: string, pageSize: number, pageNo: number): Promise<any> {
    return axios.get(`${url}/${pageSize}/${pageNo}`)
}
