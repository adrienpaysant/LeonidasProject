import http from "../http-common";

class PcapDataService {
  getAll() {
    return http.get("/pcapData");
  }

//   get(id) {
//     return http.get(`/pcapData/${id}`);
//   }

  create(data) {
    return http.post("/pcapData", data);
  }

  update(id, data) {
    return http.put(`/pcapData/${id}`, data);
  }

//   delete(id) {
//     return http.delete(`/pcapData/${id}`);
//   }

  deleteAll() {
    return http.delete(`/packetData`);
  }

//   findByTitle(title) {
//     return http.get(`/pcapData?title=${title}`);
//   }
}

export default new PcapDataService();