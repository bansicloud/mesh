package com.gentics.mesh.core.verticle.tag;

import static com.gentics.mesh.util.MeshAssert.assertSuccess;
import static com.gentics.mesh.util.MeshAssert.latchFor;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gentics.mesh.core.AbstractWebVerticle;
import com.gentics.mesh.core.rest.node.NodeListResponse;
import com.gentics.mesh.core.verticle.project.ProjectTagVerticle;
import com.gentics.mesh.demo.DemoDataProvider;
import com.gentics.mesh.graphdb.Trx;
import com.gentics.mesh.test.AbstractRestVerticleTest;

import io.vertx.core.Future;

public class ProjectTagNodeVerticleTest extends AbstractRestVerticleTest {

	@Autowired
	private ProjectTagVerticle tagVerticle;

	@Override
	public AbstractWebVerticle getVerticle() {
		return tagVerticle;
	}

	@Test
	public void testReadNodesForTag() {
		try (Trx tx = new Trx(db)) {
			Future<NodeListResponse> future = getClient().findNodesForTag(DemoDataProvider.PROJECT_NAME, tag("red").getUuid());
			latchFor(future);
			assertSuccess(future);
		}
	}
}
