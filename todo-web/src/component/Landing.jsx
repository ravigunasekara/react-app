import React, { Component } from 'react';

class Landing extends Component {

    findItems(listId) {
        this.props.history.push({
            pathname: `/list/${listId}`,
        })
    }
    componentDidMount() {

    }

    render() {
        return (
            <div className="container">
                <table className="table">
                <thead>
                        <tr>
                            <th>Owner ID (Enter new ID for new Users, eg: 100)</th>
                        </tr>  
                        </thead>             
                    <tbody>
                        <tr>
                            <td><input type="number" id="fname" name="fname" /></td>
                            <td><button className="btn btn-success" onClick={() => this.findItems(document.getElementById("fname").value)}>Find</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        )
    }
}
export default Landing