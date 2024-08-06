import {
  createBrowserRouter,
  RouteObject,
  RouterProvider,
} from 'react-router-dom';
import { PAGE_LIST } from './constants/properties';
import HomePage from './pages/HomePage';
import AboutPage from './pages/AboutPage';
import SettingsPage from './pages/SettingsPage';
import NotFoundPage from './pages/NotFoundPage';
import MissionDetailPage from './pages/MissionDetailPage';
import SolutionPage from './pages/SolutionPage';
import SuspensedErrorBoundary from './components/SuspensedErrorBoundary';

const App = () => {
  return (
    <SuspensedErrorBoundary>
      <RouterProvider router={router} />
    </SuspensedErrorBoundary>
  );
};

const routes: RouteObject[] = [
  {
    path: PAGE_LIST.HOME,
    element: <HomePage />,
  },
  {
    path: PAGE_LIST.ABOUT,
    element: <AboutPage />,
  },
  {
    path: PAGE_LIST.SETTINGS,
    element: <SettingsPage />,
  },
  {
    path: PAGE_LIST.MISSION_DETAIL,
    element: <MissionDetailPage />,
  },
  {
    path: PAGE_LIST.SOLUTION,
    element: <SolutionPage />,
  },
  {
    path: '*',
    element: <NotFoundPage />,
  },
];

const router = createBrowserRouter(routes);

export default App;
