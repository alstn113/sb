import axios from 'axios';

export interface Post {
  userId: number;
  id: number;
  title: string;
  body: string;
}

const PostAPI = {
  getPosts: async () => {
    const { data } = await axios.get<Post[]>(
      `https://jsonplaceholder.typicode.com/posts`
    );
    return data;
  },

  getPostById: async (id: number) => {
    const { data } = await axios.get<Post>(
      `https://jsonplaceholder.typicode.com/posts/${id}`
    );

    return data;
  },
};

export default PostAPI;
