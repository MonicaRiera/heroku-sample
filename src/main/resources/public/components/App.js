class App extends React.Component {

    constructor(props) {
        super(props)

        this.state = {
            message: "Hello"
        }
    }

    componentDidMount() {
        console.log("Component loaded");

        const helloPromise = axios.get("/api/hello")

        helloPromise.then(response => {
                this.setState({ message: response.data})
            });
    }

    render() {
        return(
            <div>
                <h1>Sample Heroku App</h1>
                <p>
                    {this.state.message}
                </p>
            </div>
        );
    }
}