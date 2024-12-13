import { auth } from "@/auth";
import PapersTable from "../components/papers-table";
import findPapersAction from "../../data/actions/find-papers.action";

const PapersScreen = async () => {
  const session = await auth();

  if (!session) {
    return null;
  }

  const papers = await findPapersAction(session.jwt);

  return <PapersTable papers={papers.data || []} />;
};

export default PapersScreen;
