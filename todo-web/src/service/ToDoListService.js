import axios from 'axios'

const TODOLIST_APP_URL = `http://localhost:8080/items`

class ToDoListService {

    retrieveAllItems(listId) {
        return axios.get(`${TODOLIST_APP_URL}`, {
            params: {
                listId: listId
            }
        });
    }

    deleteItem(listId, itemId) {

        return axios.delete(TODOLIST_APP_URL, {
            params: {
                listId: listId,
                itemId: itemId
            }
        });
    }

    updateItem(listId, item) {

        return axios.put(TODOLIST_APP_URL, {
            description: item.description,
            status: item.status,
            date: item.date
        }, {
            params: {
                listId: listId,
                itemId: item.itemId
            }
        }
        );
    }

    createItem(listId, item) {
        return axios.post(TODOLIST_APP_URL, {
            description: item.description,
            status: item.status,
            date: item.date
        }, {
            params: {
                listId: listId
            }
        });
    }

}

export default new ToDoListService()