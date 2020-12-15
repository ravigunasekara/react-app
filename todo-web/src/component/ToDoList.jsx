import React,  { Component } from 'react';
import {  Switch, BrowserRouter as Router, Route } from 'react-router-dom'
import ListItemsComponent from './ListItemsComponent'
import Item from './Item'
import Landing from './Landing'

class ToDoList extends Component {
    render() {
        return (
            <Router>
                <>
                <h1>ToDo-List App</h1>
                <Switch>
                        <Route path="/" exact component={Landing} />
                        <Route path="/list" exact component={Landing} />
                        <Route path="/list/:id" exact component={ListItemsComponent} />
                        <Route path="/list/:listId/items/:itemId"  exact component={Item} />
                </Switch>
              </>
            </Router>
        )
    }
}

export default ToDoList