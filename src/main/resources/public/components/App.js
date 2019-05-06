class App extends React.Component {

    constructor(props) {
        super(props)

        this.state = {
            message: "",
            words: [],
            user: {
                email: "",
                password: ""
            }
        }
    }

    componentDidMount() {
        console.log("Component loaded");

        this.loadWords();
    }

    loadWords() {
        const wordsPromise = axios.get("/api/list");

        wordsPromise.then(response => {
           this.setState({ words: response.data});
           console.log(this.words);
        });
    }

    retrieveHelloMessage() {
        const helloPromise = axios.get("/api/hello");

        helloPromise.then(response => {
            this.setState({ message: response.data});
        });
    }

    render() {
        return(
            <div>
                <h1>Sample Heroku App</h1>

                <div>
                    <div>
                        Email: <input onChange={(event) => this.updateField(event, "email")} type="text"/>
                    </div>
                    <div>
                        Password: <input onChange={(event) => this.updateField(event, "password")} type="text"/>
                    </div>
                    <div>
                        <button onClick={() => this.register()}>Sign Up</button>
                    </div>
                </div>
            </div>
        );
    }

    register() {
        const user = this.state.user;
        console.log("Registering email " + user.email + " with password " + user.password);

        const promise = axios.post("/api/login/register", user);

        promise.then(response => {
            const message = response.data;
            console.log("Message from registering: " + message);
        })
    }

    updateField(event, property) {
        console.log("input changed");

        const value = event.target.value;

        this.setState((currentState) => {
            currentState.user[property] = value;
            return currentState;
        });

    }
}