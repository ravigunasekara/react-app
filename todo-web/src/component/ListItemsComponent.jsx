import React, { Component } from 'react';
import ToDoListService from '../service/ToDoListService'

class ListItemsComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            listId: this.props.match.params.id,
            items: [],
            message: null
        }
        this.refreshItems = this.refreshItems.bind(this)
        this.deleteCourseClicked = this.deleteItem.bind(this)
        this.updateItem = this.updateItem.bind(this)
        this.addItem = this.addItem.bind(this)
        this.switchUser = this.switchUser.bind(this);
    }

    componentDidMount() {
        console.log("Mounted");
        this.refreshItems();
    }

    refreshItems() {
        console.log("Refresh ");
        ToDoListService.retrieveAllItems(this.state.listId)
            .then(
                response => {
                    console.log(response.data);
                    this.setState({ items: response.data.items })
                }
            ).catch(function (response) {
                console.log(response);
            });
    }

    updateItem(item) {
        this.props.history.push({
            pathname: `${this.state.listId}/items/${item.itemId}`,
            data: item
        })
    }

    addItem() {
        this.props.history.push(`${this.state.listId}/items/-1`)
    }

    deleteItem(id) {
        console.log(id);
        ToDoListService.deleteItem(this.state.listId, id)
            .then(
                response => {
                    this.setState({ message: `Delete of Item ${id} Successful` })
                    this.refreshItems();
                }
            )
    }

    switchUser() {
        this.props.history.push(`/list`);
    }

    render() {
        return (
            <div className="container">
                <h3>Items</h3>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Description</th>
                                <th>Date</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.items.map(
                                    item =>
                                        <tr key={item.itemId}>
                                            <td>{item.description}</td>
                                            <td>{item.date}</td>
                                            <td>{item.status}</td>
                                            <td><button className="btn btn-success" onClick={() => this.updateItem(item)}>Update</button></td>
                                            <td><button className="btn btn-warning" onClick={() => this.deleteItem(item.itemId)}>Delete</button></td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
                <div class="btn-toolbar">
                    <button className="btn btn-primary btn-sm " onClick={this.switchUser} >Switch User</button>
                    <button className="btn btn-success ml-auto" onClick={this.addItem}>Add Item</button>
                </div>

            </div>
        )
    }
}

export default ListItemsComponent